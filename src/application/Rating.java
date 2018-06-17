package application;

public class Rating {

	private String title;
	private double rating;
	
	Rating(String title, Double rating){
		this.title = title;
		this.rating = rating;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setRating(Double rating){
		this.rating = rating;
	}
	
	public Double getRating(){
		return this.rating;
	}
}
