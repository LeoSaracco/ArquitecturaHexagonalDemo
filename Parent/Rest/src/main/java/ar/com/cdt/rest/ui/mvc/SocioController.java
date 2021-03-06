/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package ar.com.cdt.rest.ui.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.cdt.rest.ui.SocioRepository;
import ar.com.cdt.socios.Socio;

/**
 * @author Rob Winch
 */
@Controller
@RequestMapping("/")
public class SocioController {
	private final SocioRepository socioRepository;

	@Autowired
	public SocioController(SocioRepository socioRepository) {
		this.socioRepository = socioRepository;
	}

	@RequestMapping
	public ModelAndView list() {
		Iterable<Socio> socios = this.socioRepository.findAll();
		return new ModelAndView("socios/list", "socios", socios);
	}

	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Socio socio) {
		return new ModelAndView("socios/view", "socio", socio);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Socio socio) {
		return "socios/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid Socio socio, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("socios/form", "formErrors", result.getAllErrors());
		}
		socio = this.socioRepository.save(socio);
		redirect.addFlashAttribute("globalMessage", "Socio creado correctamente");
		return new ModelAndView("redirect:/{socio.id}", "socio.id", socio.getId());
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

}
