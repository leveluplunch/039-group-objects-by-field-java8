package com.levelup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class GroupByField {

	class Stock {

		String symbol;
		String sold;
		Integer shares;

		public Stock(String symbol, String sold, Integer shares) {
			super();
			this.symbol = symbol;
			this.sold = sold;
			this.shares = shares;
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public String getSold() {
			return sold;
		}

		public void setSold(String sold) {
			this.sold = sold;
		}

		public Integer getShares() {
			return shares;
		}

		public void setShares(Integer shares) {
			this.shares = shares;
		}

		@Override
		public String toString() {
			return "Stock [symbol=" + symbol + ", sold=" + sold + ", shares="
					+ shares + "]";
		}
	}

	private List<Stock> stock;

	@Before
	public void setUp() {

		stock = new ArrayList<>();
		stock.add(new Stock("GOOGL", "N", 10));
		stock.add(new Stock("AAPL", "N", 5));
		stock.add(new Stock("YHOO", "N", 3));
		stock.add(new Stock("AAPL", "N", 9));
		stock.add(new Stock("GOOGL", "Y", 2));
		stock.add(new Stock("GOOGL", "Y", 5));
	}

	@Test
	public void group_by_symbol_total_shares() {

		Map<String, List<Stock>> stockBySymbol = stock.stream().collect(
				Collectors.groupingBy(Stock::getSymbol));

		System.out.println(stockBySymbol);
	}

	@Test
	public void group_by_symbol_traded() {

		Map<String, Integer> stockBySymbol = stock.stream().collect(
				Collectors.groupingBy(Stock::getSymbol,
						Collectors.summingInt(Stock::getShares)));

		System.out.println(stockBySymbol);

	}

	@Test
	public void group_by_symbol_outstanding() {

		Map<String, Integer> stockBySymbol = stock.stream()
				.filter(stock -> stock.getSold().equals("N"))
				.collect(
				Collectors.groupingBy(Stock::getSymbol,
						Collectors.summingInt(Stock::getShares)));

		System.out.println(stockBySymbol);
	}

}
