package joejava.soap;

import java.math.BigDecimal;

public class StockBean {
	private String symbol;
	private BigDecimal last;
	private String date;
	private String time;
	private BigDecimal change;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private int volume;
	private String mktCap;
	private BigDecimal previousClose;
	private String percentageChg;
	private String annRange;
	private BigDecimal earns;
	private BigDecimal PE;
	private String name;
	/*
	public StockBean(noNamespace.Stock stock){
		this.symbol = stock.getSymbol();
		this.last = stock.getLast();
		this.date = stock.getDate();
		this.time = stock.getTime();
		this.change = stock.getChange();
		this.open = stock.getOpen();
		this.high = stock.getHigh();
		this.low = stock.getLow();
		this.volume = stock.getVolume().intValue();
		this.mktCap = stock.getMktCap();
		this.previousClose = stock.getPreviousClose();
		this.percentageChg = stock.getPercentageChange();
		this.annRange = stock.getAnnRange();
		this.earns = stock.getEarns();
		this.PE = stock.getPE();
		this.name = stock.getName();
	}
	*/
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getLast() {
		return last;
	}
	public void setLast(BigDecimal last) {
		this.last = last;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	/*
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	*/
	public BigDecimal getChange() {
		return change;
	}
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getMktCap() {
		return mktCap;
	}
	public void setMktCap(String mktCap) {
		this.mktCap = mktCap;
	}
	public BigDecimal getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}
	public String getPercentageChg() {
		return percentageChg;
	}
	public void setPercentageChg(String percentageChg) {
		this.percentageChg = percentageChg;
	}
	public String getAnnRange() {
		return annRange;
	}
	public void setAnnRange(String annRange) {
		this.annRange = annRange;
	}
	public BigDecimal getEarns() {
		return earns;
	}
	public void setEarns(BigDecimal earns) {
		this.earns = earns;
	}
	public BigDecimal getPE() {
		return PE;
	}
	public void setPE(BigDecimal pe) {
		PE = pe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
