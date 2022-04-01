/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ar.com.cdt.rest.ui;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import ar.com.cdt.socios.Socio;

/**
 * @author Dave Syer
 */
public class InMemorySocioRespository implements SocioRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, Socio> socios = new ConcurrentHashMap<Long, Socio>();

	@Override
	public Iterable<Socio> findAll() {
		return this.socios.values();
	}

	@Override
	public Socio save(Socio socio) {
		Long id = socio.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			socio.setId(id);
		}
		this.socios.put(id, socio);
		return socio;
	}

	@Override
	public Socio findSocio(Long id) {
		return this.socios.get(id);
	}

}
