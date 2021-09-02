package com.danicode.springboot.app.util.paginator;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	private String url;
	private Page<T> pagina;
	private int totalPaginas;
	private int elementosPorPaginas;
	private int paginaActual;
	private List<PageItem> paginas;

	public PageRender(String url, Page<T> pagina) {
		this.url = url;
		this.pagina = pagina;
		this.paginas = new LinkedList<PageItem>();
		elementosPorPaginas = pagina.getSize();
		totalPaginas = pagina.getTotalPages();
		paginaActual = pagina.getNumber() + 1; // Inicia de cero

		int desde, hasta;

		if (totalPaginas <= elementosPorPaginas) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if (paginaActual <= elementosPorPaginas / 2) {
				desde = 1;
				hasta = elementosPorPaginas;
			} else if (paginaActual >= totalPaginas - (elementosPorPaginas / 2)) {
				desde = totalPaginas - elementosPorPaginas;
				hasta = elementosPorPaginas;
			} else {
				desde = paginaActual - (elementosPorPaginas / 2);
				hasta = elementosPorPaginas;
			}
		}

		for (int i = 0; i < hasta; i++) {
			this.paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return pagina.isFirst();
	}

	public boolean isLast() {
		return pagina.isLast();
	}

	public boolean isHasNext() {
		return pagina.hasNext();
	}

	public boolean isHasPrevious() {
		return pagina.hasPrevious();
	}
}
