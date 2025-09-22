package com.example.demo.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.example.demo.exceptions.SavingErrorException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.GradDTO;
import com.example.demo.dto.NekretninaDTO;
import com.example.demo.dto.ObilazakDTO;
import com.example.demo.dto.TipnekretnineDTO;
import com.example.demo.repositories.GradRepository;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.NekretninaRepository;
import com.example.demo.repositories.ObilazakRepository;
import com.example.demo.repositories.OmiljeneRepository;
import com.example.demo.repositories.SlikaRepository;
import com.example.demo.repositories.TipNekretnineRepository;
import com.example.demo.repositories.UlogaRepository;

import model.Grad;
import model.Korisnik;
import model.Nekretnina;
import model.Obilazak;
import model.Omiljene;
import model.Slika;
import model.Tipnekretnine;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class ServiceP {
	
	@Autowired
	GradRepository gr;
	
	@Autowired
	TipNekretnineRepository tnr;
	
	@Autowired
	UlogaRepository ur;
	
	@Autowired
	SlikaRepository sr;
	
	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	OmiljeneRepository omr;
	
	@Autowired
	ObilazakRepository or;
	
	@Autowired
	NekretninaRepository nr;
	
	public Korisnik getKorisnik() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Korisnik ulogovaniKorisnik = kr.findByUsername(currentPrincipalName);
		return ulogovaniKorisnik;
	}

	@Transactional
	public List<Nekretnina> getNekretnine() {
		List<Nekretnina> lista = nr.findAll();
		for (Nekretnina n : lista) {
			n.getSlikas().size();
		}
		return lista;
	}


	public List<Nekretnina> getNekretnineKorisnika() {
		Korisnik k = getKorisnik();
		if(k == null) return null;
        return k.getNekretninas();
	}
	
	public Nekretnina getNekretnina(Integer id) {
		return nr.findById(id).get();
	}
	
//	public InputStream fetchImage(String imageUrl) throws IOException {
//        URL url = new URL(imageUrl);
//        return url.openStream();
//    }
	
//	public List<InputStream> slike(List<Slika> slike) throws IOException {
//		List<InputStream> strimovi = new ArrayList<>();
//		for(Slika s : slike) {
//			strimovi.add(fetchImage(s.getUrl()));
//		}
//		return strimovi;
//	}
	
	public List<Tipnekretnine> getTipoviBaza() {
		return tnr.findAll();
	}
	
	public List<Grad> getGradoviBaza() {
		return gr.findAll();
	}
	
	public List<TipnekretnineDTO> getTipovi() {
		List<Tipnekretnine> tipovi = tnr.findAll();
		if(tipovi == null) return null;
		List<TipnekretnineDTO> tipoviDTO = new ArrayList<>();
		for(Tipnekretnine tn : tipovi) {
			TipnekretnineDTO tnDTO = new TipnekretnineDTO();
			tnDTO.setNaziv(tn.getNaziv());
			tipoviDTO.add(tnDTO);
		}
		return tipoviDTO;
	}
	
	public List<GradDTO> getGradovi() {
		List<Grad> gradovi = gr.getSorted();
		if(gradovi == null) return null;
		List<GradDTO> gradoviDTO = new ArrayList<>();
		for(Grad g : gradovi) {
			GradDTO gDTO = new GradDTO();
			gDTO.setNaziv(g.getNaziv());
			gradoviDTO.add(gDTO);
		}
		return gradoviDTO;
	}
	
	public Grad getGrad(String naziv) {
		return gr.findByNaziv(naziv);
	}
	
	public Tipnekretnine getTip(String naziv) {
		return tnr.findByNaziv(naziv);
	}
	
	public List<Grad> getGradovi2() {
		return gr.getSorted();
	}

	@Transactional
	public List<Slika> getSlike(Integer id) {
		Optional<Nekretnina> opt = nr.findById(id);
		if(opt.isPresent()) {
			Nekretnina n = opt.get();
			Hibernate.initialize(n.getSlikas());
			return n.getSlikas();
		}
		return Collections.emptyList();
	}

	@Transactional
	public int saveNekretnina(NekretninaDTO nekretnina, String tip, String grad, MultipartFile[] slike) {
		Nekretnina novan = new Nekretnina();
		novan.setBrojSpavacihSoba(nekretnina.getBrojSpavacihSoba());
		novan.setBrojKupatila(nekretnina.getBrojKupatila());
		novan.setKvadratura(nekretnina.getKvadratura());
		novan.setAdresa(nekretnina.getAdresa());
		novan.setCena(nekretnina.getCena());
		novan.setOpis(nekretnina.getOpis());
		novan.setGrad(gr.findByNaziv(nekretnina.getGrad()));
		novan.setTipnekretnine(tnr.findByNaziv(nekretnina.getTip()));
		novan.setProdavac(getKorisnik());
		try {
			Nekretnina n = nr.save(novan);
//			System.out.println("Id nekretnine n je " + n.getIdNekretnina());
//			System.out.println("Id nekretnine novan je: " + novan.getIdNekretnina());
//			System.out.println("Broj slika je " + slike.length);
			for (int i=0; i<slike.length; i++) {
//				String fileName = slike[i].getOriginalFilename() + i;
				String fileName = saveImage(slike[i], i);
				System.out.println("filename slike " + i + " je " + fileName);
				System.out.println("i je " + i);
				Slika slika = new Slika();
	            System.out.println("Posle new slika " + i);
				slika.setUrl(fileName);
				System.out.println("Posle seturl " + i);
				slika.setNekretnina(n);
				System.out.println("Posle setnekretnina " + i);
				Slika s = sr.save(slika);
				System.out.println("Posle save " + i);
				n.addSlika(s);
				System.out.println("Posle n.add(s) " + i);
				System.out.println("Url slike je " + s.getUrl());
			}
			return n.getIdNekretnina();
		} catch(Exception ex) {
			System.out.println("Desio se exception");
			return -1;
		}
	}

	private static final String path = "uploads/";

	public String saveImage(MultipartFile file, int index) {
		String originalFileName = file.getOriginalFilename();
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String fileName = UUID.randomUUID().toString() + "_" + index + fileExtension;

		try {
			FileCopyUtils.copy(file.getBytes(), Paths.get(path, fileName).toFile());
			System.out.println("Saved image to: " + Paths.get(path, fileName).toAbsolutePath());
		} catch (IOException e) {
			throw new RuntimeException("Greška pri čuvanju slike: " + fileName, e);
		}

		return fileName;
	}
	
	@Transactional
	public boolean deleteNekretnina(Integer id) {
		try {
			nr.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	@Transactional
	public int dodajUOmiljene(Integer idN) {
		Nekretnina n = nr.findById(idN).get();
		Korisnik k = getKorisnik();
		if(omr.findByKorisnikAndNekretnina(k, n) != null) return -1;
		Omiljene om = new Omiljene();
		om.setKorisnik(k);
		om.setNekretnina(n);
		try {
			Omiljene o = omr.save(om);
			return o.getIdOmiljene();
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Transactional
	public List<Nekretnina> getOmiljene() {
		Korisnik k = getKorisnik();
		if(k == null) return null;
		List<Omiljene> omiljene = k.getOmiljenes();
		if(omiljene == null) return null;
		List<Nekretnina> nekretnine = new ArrayList<>();
		for(Omiljene o : omiljene) {
			nekretnine.add(o.getNekretnina());
		}
		return nekretnine;
	}
	
	@Transactional
	public boolean deleteOmiljena(Integer idN) {
		Nekretnina n = nr.findById(idN).get();
		Korisnik k = getKorisnik();
		Omiljene o = omr.findByKorisnikAndNekretnina(k, n);
		if(o == null) return false;
		omr.deleteById(o.getIdOmiljene());
		return true;
	}
	
	@Transactional
	public Integer saveObilazak(ObilazakDTO obilazak, Integer idN) {
		Obilazak novio = new Obilazak();
//		if(obilazak.getDatum() == null) return -5;
		if(obilazak.getDatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())) {
//			System.out.println("Nije dobar datum");
			return -2;
		}
		novio.setDatum(obilazak.getDatum());
		Nekretnina n = nr.findById(idN).orElse(null);
		if(n == null) return -3;
//		System.out.println("Nekretnina postoji");
		novio.setNekretnina(n);
		Korisnik k = getKorisnik();
		if(k == null) return -4;
//		System.out.println("Korisnik postoji");
		novio.setKorisnik(k);
		try {
			Obilazak o = or.save(novio);
//			System.out.println("Id " + o.getIdObilazak());
			return o.getIdObilazak();
		}catch(Exception ex) {
			System.out.println("Greska tokom cuvanja");
			return -1;
		}
	}
	
	@Transactional
	public List<Obilazak> getObilasciKorisnika() {
		Korisnik k = getKorisnik();
		if(k == null) return null;
		return k.getObilazaks();
	}
	
	@Transactional
	public boolean deleteObilazak(Integer idO) {
		try {
			or.deleteById(idO);
		}catch(Exception ex) {
			return false;
		}
		return true;
	}
	
	public List<Nekretnina> searchNekretnina(
            String tipNekretnine,
            String grad,
            Integer minPrice,
            Integer maxPrice,
            Integer minSize,
            Integer maxSize,
            Integer minBedrooms,
            Integer minBathrooms) {
//		System.out.println("Krenuo servis....");
//		List<Nekretnina> nek = nr.findByCriteria(tipNekretnine, grad, minPrice, maxPrice, minSize, maxSize, minBedrooms,
//                minBathrooms);
//		for(Nekretnina n : nek) {
//			System.out.println(n.getCena());
//		}
//		if (nek != null)
//			System.out.println(nek.get(0).getProdavac().getUsername());
        return nr.findByCriteria(
                tipNekretnine,
                grad,
                minPrice,
                maxPrice,
                minSize,
                maxSize,
                minBedrooms,
                minBathrooms);
    }
	
	public byte[] kreirajIzvestaj1(String grad) throws JRException, IOException {
		System.out.println(omr.getOmiljenesByGrad(grad).size());
		if(omr.getOmiljenesByGrad(grad).size() > 0) {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(omr.getOmiljenesByGrad(grad));
			InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/izvestajOmiljene.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("grad", grad);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			inputStream.close();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			exporter.exportReport();
			return byteArrayOutputStream.toByteArray();
		}
		return null;
	}
	
	public byte[] kreirajIzvestaj2(String tip, Integer cena) throws JRException, IOException {
		System.out.println(nr.getNekretnineByTip(tip, cena).size());
		if(nr.getNekretnineByTip(tip, cena).size() > 0) {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(nr.getNekretnineByTip(tip, cena));
			InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/izvestajNajskuplje.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tipNekretnine", tip);
			params.put("cenaN", cena);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			inputStream.close();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			exporter.exportReport();
			return byteArrayOutputStream.toByteArray();
		}
		return null;
	}
	
	public byte[] kreirajIzvestaj3(Date datum, String tip) throws JRException, IOException {
		System.out.println(or.obilasciPoDatumuITipu(datum, tip));
		if(or.obilasciPoDatumuITipu(datum, tip).size() > 0) {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(or.obilasciPoDatumuITipu(datum, tip));
			InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/izvestajObilasci.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tipNekretnine", tip);
			params.put("datumOb", datum);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			inputStream.close();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			exporter.exportReport();
			return byteArrayOutputStream.toByteArray();
		}
		return null;
	}
	
//	public JasperPrint izvestaj1(String grad) throws JRException, IOException {
//		System.out.println("Broj omiljenih " + omr.getOmiljenesByGrad(grad).size());
//		System.out.println("Grad: " + grad);
//		if(omr.getOmiljenesByGrad(grad).size() > 0) {
//			System.out.println("Service: izvestaj nije null");
//			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(omr.getOmiljenesByGrad(grad));
//			InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/izvestajOmiljene.jrxml");
//			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("grad", grad);
//			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//			inputStream.close();
//			return jasperPrint;
//		}
//		System.out.println("izvestaj je null");
//		return null;
//	}

}
