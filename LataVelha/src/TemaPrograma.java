import java.util.ArrayList;
import java.util.List;

public class TemaPrograma {
	private int idEvaluation;
	private List<String> contents;
	
	public TemaPrograma(int idEvaluation) {
		
		this.idEvaluation = idEvaluation;
		this.contents = new ArrayList<String>();
	}
	
	public int pegaId() {
		return this.idEvaluation;
	}
	
	public List<String> pegaConteudo() {
		return this.contents;
	}

	public void addConteudo(String content) {
		this.contents.add(content);
	}
}
