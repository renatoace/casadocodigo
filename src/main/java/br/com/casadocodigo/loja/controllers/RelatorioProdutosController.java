package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.RelatorioProdutos;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public RelatorioProdutos relatorioProdutosJson() {
		return dao.gerarRelatorio();
	}
	
	@RequestMapping(params = "data", method=RequestMethod.GET)
	@ResponseBody
	public RelatorioProdutos relatorioProdutosJson(@RequestParam("data") String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(data));
		return dao.gerarRelatorio(calendar);
	}
	

	
}
