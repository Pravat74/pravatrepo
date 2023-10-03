package com.pravat.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorDetails {
	
	private LocalDateTime time;
	private String msg;
	private String status;

}
