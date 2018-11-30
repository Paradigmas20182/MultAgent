import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AgentePlateia extends Agent{
	
	private static final long serialVersionUID = -587032368657425161L;
	private boolean ganhou = true;

	protected void setup() {
		ServiceDescription sd = new ServiceDescription(); 
		sd.setName(getName()); 
		sd.setType("AgentePlateia"); 
		register(sd);
		
		addBehaviour(new CyclicBehaviour(this) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				ACLMessage message = myAgent.receive();
				
				if (message != null) {
					ACLMessage reply = message.createReply();
					String messageContent = message.getContent();
					if(message.getPerformative() == ACLMessage.INFORM) {						
							System.out.println(getName() + ": \t" + messageContent);
							String replyMessage = analyseFirstEvaluation(messageContent);
							
							reply.setPerformative(ACLMessage.INFORM);

							reply.setContent(replyMessage);
							myAgent.send(reply);
					}
					else if (message.getPerformative() == ACLMessage.REQUEST) {
						reply.setPerformative(ACLMessage.INFORM);
						
						if(ganhou){
							reply.setContent("A plateia gostou da apresentação e decidiu que o participante merece seu carro de volta.");
						}
						else{
							reply.setContent("A plateia não gostou da apresentação e decidiu que o participante não merece seu carro de volta.");
						}
						
						myAgent.send(reply);
					}
				} else {
					block();
				}
				
			}
		});	
				
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
    
    
    private String analyseFirstEvaluation(String message) {
    	double grade = getNote(message);
    	String feedback = null;
    	if(grade < 3) {
			feedback = "Plateia achou Ruim e vaiou";
    		System.out.println(getName() + ": \t" + feedback);
    		this.ganhou = false;
		}else if(grade >= 3 && grade < 6) {
			feedback = "Plateia achou regular e ficou dividida";
    		System.out.println(getName() + ": \t" + feedback);
    		this.ganhou = false;
		}else if(grade >= 6 && grade < 9){
			feedback = "Plateia achou bom e boa parte aplaudiu";
    		System.out.println(getName() + ": \t" + feedback);
		}else {
			feedback = "Plateia achou exelente e aplaudiu de pe";
    		System.out.println(getName() + ": \t" + feedback);
		}
    		
    	return feedback;
    }
    
    private double getNote(String message) {
    	String [] split = message.split(" ");
    	double note = Double.parseDouble(split[3]);
    	return note;
    }

}
