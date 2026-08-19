package com.example.common.lib.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T> {

	private List<T> content;

	private int pageNumber;

	private int pageSize;

	private long totalElements;

	private int totalPages;

	private boolean first;

	private boolean last;

}
