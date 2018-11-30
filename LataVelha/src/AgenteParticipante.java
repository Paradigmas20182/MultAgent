import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteParticipante extends Agent{
		
		private static final long serialVersionUID = -6712087013514278403L;
		private AID companion;
		
		public AgenteParticipante() {

		}
		
		protected void setup() {
			ServiceDescription sd = new ServiceDescription(); 
			sd.setName(getName()); 
			sd.setType("AgenteParticipante"); 
			register(sd);
			
			addBehaviour(new BehaviourCriarPlateia(this));

			AvaliaFeedback utils = new AvaliaFeedback();
			
			// Set Student note
			double performace = utils.generateRandomDouble(0, 10);		
			String grade = String.valueOf(performace);
			String messageFirstEvaluation = "Performace do participante "+ grade;
			addBehaviour(new BehaviourEnviarMensagem(this, messageFirstEvaluation));
		}
		
		
	    void register( ServiceDescription sd)
	    {
	        DFAgentDescription dfd = new DFAgentDescription();
	        dfd.setName(getAID());
	        dfd.addServices(sd);

	        try {  
	            DFService.register(this, dfd );  
	        }
	        catch (FIPAException fe) { fe.printStackTrace(); }
	    }

		public void setCompanion(AID companion) {
			this.companion = companion;
		}
		
		public AID getCompanion(){
			return this.companion;
		}
}
