package WebApplication.covid_tracker;

public class model_location_stats {

	private String state;
	private String country;
	private int latest_reported;
	private int diff_prev;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatest_reported() {
		return latest_reported;
	}
	public void setLatest_reported(int latest_reported) {
		this.latest_reported = latest_reported;
	}
	@Override
	public String toString() {
		return "model_location_stats [state=" + state + ", country=" + country + ", latest_reported=" + latest_reported
				+ "]";
	}
	public int getDiff_prev() {
		return diff_prev;
	}
	public void setDiff_prev(int diff_prev) {
		this.diff_prev = diff_prev;
	}
	
}
