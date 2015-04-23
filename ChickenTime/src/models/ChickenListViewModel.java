package models;

public class ChickenListViewModel {

	private String start;
	private String elapsedTime;
	private String remainingTime;
	private boolean survived;	
	
	public boolean isSurvived() {
		return survived;
	}
	public void setSurvived(boolean survived) {
		this.survived = survived;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	

}
