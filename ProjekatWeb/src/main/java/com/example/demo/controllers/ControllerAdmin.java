package com.example.demo.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exceptions.EmptyFieldsException;
import com.example.demo.exceptions.NotfoundException;
import com.example.demo.services.ServiceP;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@Controller
//@ControllerAdvice
@RequestMapping("/admin/")
public class ControllerAdmin {
	
	@Autowired
	ServiceP service;
	
	@GetMapping("izvestaj1")
	public String izvestaj1(Model m, HttpServletRequest request) {
		m.addAttribute("gradovi", service.getGradovi());
		request.getSession().removeAttribute("greskaIzvestaj1");
		return "izvestaj1";
	}
	
//	@GetMapping("getIzvestaj")
//	public String getIzvestaj(@RequestParam("selectovani") String g, HttpServletResponse response,
//			HttpServletRequest request, Model m) throws JRException, IOException {
//		try {
//			System.out.println("Generisanje reporta:");
//			JasperPrint jasperReport = service.izvestaj1(g);
//			System.out.println("Report generisan");
//			if (jasperReport == null) {
//				System.out.println("Report je null");
////				m.addAttribute("error", "Nema izvestaja za dati grad!");
////				response.sendRedirect("/Projekat/izvestaj1");
////				response.sendError(404, "Nema izvestaja za dati grad!");
//				request.getSession().setAttribute("greskaIzvestaj1", "Nema izvestaja za dati grad!");
//				return "izvestaj1";
//			}
//			System.out.println("Report nije null");
//			response.setContentType("text/html");
//			response.setContentType("application/x-download");
//			response.addHeader("Content-disposition", "attachment; filename=izvestajOmiljene" + g + ".pdf");
//			OutputStream out = response.getOutputStream();
//			JasperExportManager.exportReportToPdfStream(jasperReport, out);
//			out.close();
//		} catch (Exception e) {
//			m.addAttribute("errorMessage", "Nema izvestaja za dati grad!");
//			request.getSession().setAttribute("greskaIzvestaj1", "Desila se greška prilikom generisanja izveštaja!");
//			response.sendRedirect("izvestaj1");
//			return "izvestaj1";
//		}
//		return "glavna";
//	}
	
	@GetMapping("getIzvestaj")
	public ResponseEntity<?> kreirajIzvestaj(@RequestParam("selectovani") String g, HttpServletResponse response,
			HttpServletRequest request, Model m) throws JRException, IOException {
		byte[] izvestaj = service.kreirajIzvestaj1(g);
		request.removeAttribute("greskaIzvestaj1");
		if(izvestaj == null) {
			request.getSession().setAttribute("greskaIzvestaj1", "Nema izvestaja za dati grad!");
//			izvestaj1(m, request);
			throw new NotfoundException("Nema izvestaja za dati grad", g);
		}
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData("attachment", "izvestajOmiljene.pdf");
        return ResponseEntity.ok()
            .headers(headers)
            .body(izvestaj);
	}
	
	@GetMapping("izvestaj2")
	public String izvestaj2(Model m, HttpServletRequest request) {
		m.addAttribute("tipovi", service.getTipovi());
		request.getSession().removeAttribute("greskaIzvestaj2");
		return "izvestajNajskuplje";
	}
	
	@GetMapping("getIzvestaj2")
	public ResponseEntity<?> kreirajIzvestaj2(@RequestParam("selectovani") String tip, @RequestParam("cena")Integer cena, HttpServletResponse response,
			HttpServletRequest request, Model m) throws JRException, IOException {
		if(cena == null || cena == 0 || cena.toString() == null) {
			throw new EmptyFieldsException("Morate uneti cenu!");
		}
		byte[] izvestaj = service.kreirajIzvestaj2(tip, cena);
		request.removeAttribute("greskaIzvestaj2");
		if(izvestaj == null) {
			request.getSession().setAttribute("greskaIzvestaj2", "Nema izvestaja za izabrane podatke!");
//			izvestaj1(m, request);
			throw new NotfoundException("Nema izvestaja za izabrane podatke!", tip);
		}
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData("attachment", "izvestajNajskuplje.pdf");
        return ResponseEntity.ok()
            .headers(headers)
            .body(izvestaj);
	}
	
	@GetMapping("izvestaj3")
	public String izvestaj3(Model m, HttpServletRequest request) {
		m.addAttribute("tipovi", service.getTipovi());
		request.getSession().removeAttribute("greskaIzvestaj3");
		return "izvestajObilasci";
	}
	
	@GetMapping("getIzvestaj3")
	public ResponseEntity<?> kreirajIzvestaj3(@RequestParam("selectovani") String tip, @RequestParam("datum")String datum, HttpServletResponse response,
			HttpServletRequest request, Model m) throws JRException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sdf.parse(datum);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new NotfoundException("Nije dobro formatiran datum", datum);
		}
		byte[] izvestaj = service.kreirajIzvestaj3(d, tip);
		request.removeAttribute("greskaIzvestaj3");
		if(izvestaj == null) {
			request.getSession().setAttribute("greskaIzvestaj3", "Nema izvestaja za izabrane podatke!");
//			izvestaj1(m, request);
			throw new NotfoundException("Nema izvestaja za izabrane podatke!", tip);
		}
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData("attachment", "izvestajObilasci.pdf");
        return ResponseEntity.ok()
            .headers(headers)
            .body(izvestaj);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleNotFoundZException(RuntimeException e) {
		return ResponseEntity.badRequest().body("Greška: " + e.getMessage());
	}

}
