import java.util.List;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AgenteApresentador extends Agent{
	
	private static final long serialVersionUID = 3039621223253454417L;
	
	public AgenteApresentador() {
		
	}
	
	protected void setup() {
		ServiceDescription sd = new ServiceDescription(); 
		sd.setName(getName()); 
		sd.setType("AgenteApresentador"); 
		register(sd);
		
		System.out.println(getName() + ": \t" + "Eu sou um apresentador, e preciso saber da plateia se o participante merece de volta seu carro reformado!");

		AvaliaFeedback utils = new AvaliaFeedback();
		
		String carro = utils.selecionaCarro();
		String desafio = utils.selecionaTema();
		
		
		TemaPrograma programa = new TemaPrograma(1);
		programa.addConteudo(carro);
		programa.addConteudo(desafio);
		apresentaTema(programa.pegaId(), programa.pegaConteudo());
		
		BehaviourConhecerPlateia cp = new BehaviourConhecerPlateia(this);
		addBehaviour(cp);
		
		addBehaviour(new CyclicBehaviour(this) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				ACLMessage message = myAgent.receive();
				
				if (message != null) {
					String messageContent = message.getContent();
					if(message.getPerformative() == ACLMessage.INFORM) {						
							System.out.println(getName() + "  resposta recebida : \t" + messageContent);
					}
				} else {
					block();
				}
				
			}
		});
		
	} 
	
	private void apresentaTema(int IDprograma, List<String> conteudo) {
		
		System.out.println(getName() + ": \t" + "ID Programa " + IDprograma);
		System.out.println(getName() + ": \t" + "O carro escolhido para ser reformado hj e o tema do desafio são respectivamente: ");
		
		for(String i : conteudo) {
			System.out.println(getName() + ": \t" + i);
		}	
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
}
