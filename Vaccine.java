
public class Vaccine {
	String nameOfVaccine[]= {"Pfizer","Moderna","Johnson & Johnson"};
	int stock[]= {100,100,100};
	public int getStock(int index) {
		return stock[index];
	}
	public String getVacName(int index) {
		return nameOfVaccine[index];
	}
	public void setStock(int index,int st) {
		stock[index]=st;
	}
	public void updateStock(int index, int newstock) {
		stock[index]=stock[index]+newstock;
	}
}
