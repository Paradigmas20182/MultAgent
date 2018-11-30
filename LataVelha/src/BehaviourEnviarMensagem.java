import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourEnviarMensagem extends OneShotBehaviour{
	private String content;
	public BehaviourEnviarMensagem(AgenteParticipante a, String content) {
		super(a);
		this.content = content;
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		
		ACLMessage message = new ACLMessage(ACLMessage.INFORM);
		message.addReceiver(((AgenteParticipante) myAgent).getCompanion());
		message.setLanguage("Português");
		message.setOntology("FeedBack");
		message.setContent(content);
		myAgent.send(message);	
	}
}
