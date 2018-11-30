import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class BehaviourConhecerPlateia extends CyclicBehaviour{
	
	private static final long serialVersionUID = 7268217846531384442L;
	
	public BehaviourConhecerPlateia(Agent a) { 
		super(a); 
	}


	public void action() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd  = new ServiceDescription();
        sd.setType("AgentePlateia");                                                    
        dfd.addServices(sd);
        block(1000);
        
        DFAgentDescription[] result;
		SearchConstraints all = new SearchConstraints();
		all.setMaxResults(new Long(-1));
		try {
			result = DFService.search(this.getAgent(), dfd, all);
			if(result.length >= 1) {
				AID[] agents = new AID[result.length];
				for (int i=0; i<result.length; i++) {
					agents[i] = result[i].getName();
				}
				
				
				this.getAgent().addBehaviour(new BahaviourFeedbackPlateia(this.getAgent(), agents));
				this.getAgent().removeBehaviour(this);
            }
			else {
				block();
			}
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}
}
