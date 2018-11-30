import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class BahaviourFeedbackPlateia extends OneShotBehaviour{
	
	private AID[] companions;
	
	private static final long serialVersionUID = -4956502801288020171L;
	
	public BahaviourFeedbackPlateia(Agent a, AID[] companions) {
		super(a);
		this.companions = companions;
	}
	
	@Override
	public void action() {
		for (AID aid : companions) {
			ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
			message.addReceiver(aid);
			message.setLanguage("Português");
			message.setOntology("Acompanhamento");
			String mensagem = "O participante merece seu carro de volta?";
			System.out.println(myAgent.getName() + ": \t enviando mensagem para coletar feedback da plateia: " + aid);
			message.setContent(mensagem);
			myAgent.send(message);	
		}	
	}

}
