import java.util.Random;

public class AvaliaFeedback {
	
	public AvaliaFeedback() {
		
	}
	
	public int generateRandomNumber(int range) {
		Random rand = new Random();
		return  rand.nextInt(range);
	}
	
	public double generateRandomDouble(int min, int max) {
		Random r = new Random();
		double randomValue = min + (max - min) * r.nextDouble();
		return randomValue;

	}
	
	public String selecionaCarro() {
		
		String [] possibleContents = {"Opala ", "Gol Quadrado ", "Belina ",
									 "Fusca ","Kombi ", "Chevette ", "Monza ",
									 "Santana ","Relampago Marquinhos ","Brasilia "};
		
		int index = generateRandomNumber(8);
		String content = possibleContents[index];
		return content;
	}
	
	
	public String selecionaTema() {
		String [] possibleContents = {"Cantar", "Dancar", "Fazer Magica"};

		int index = generateRandomNumber(2);
		String content = possibleContents[index];
		return content;
	}
}
