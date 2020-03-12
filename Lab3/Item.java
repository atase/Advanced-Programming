interface Item{
	
	public void setName(String name);
	public String getName();

	default float profitFactor(float value, float weight){
		return value/weight;
	}
}