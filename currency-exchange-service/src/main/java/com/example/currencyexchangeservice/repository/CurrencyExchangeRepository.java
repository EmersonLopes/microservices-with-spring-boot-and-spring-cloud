package com.example.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.currencyexchangeservice.model.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	
	public CurrencyExchange findByFromAndTo(String from, String to);

}
