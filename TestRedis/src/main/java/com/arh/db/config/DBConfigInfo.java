package com.arh.db.config;

public class DBConfigInfo {

	private String name;

	private int maxTotal = 8;

	private int maxIdle = 8;

	private int minIdle = 0;

	private int initialSize = 0;

	private String validationQuery;

	private boolean testWhileIdle = false;

	private boolean testOnBorrow = true;

	private boolean testOnReturn = false;

	private long maxWaitMillis = -1;

	private long timeBetweenEvictionRunsMillis = -1;

	private int numTestsPerEvictionRun = 3;

	private long minEvictableIdleTimeMillis = 1000 * 60 * 30;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	@Override
	public String toString() {
		return "DsConfig [name=" + name + ", maxTotal=" + maxTotal + ", maxIdle=" + maxIdle + ", minIdle=" + minIdle
				+ ", initialSize=" + initialSize + ", validationQuery=" + validationQuery + ", testWhileIdle="
				+ testWhileIdle + ", testOnBorrow=" + testOnBorrow + ", testOnReturn=" + testOnReturn
				+ ", maxWaitMillis=" + maxWaitMillis + ", timeBetweenEvictionRunsMillis="
				+ timeBetweenEvictionRunsMillis + ", numTestsPerEvictionRun=" + numTestsPerEvictionRun
				+ ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis + "]";
	}


}
