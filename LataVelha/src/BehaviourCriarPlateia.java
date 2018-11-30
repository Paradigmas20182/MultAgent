import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class BehaviourCriarPlateia extends OneShotBehaviour{

	private static final long serialVersionUID = -896018215576837891L;
	
	public BehaviourCriarPlateia(AgenteParticipante a) {
		super(a);
	}
	
	@Override
	public void action() {
		try {
				AgentePlateia plateia = new AgentePlateia();
				ContainerController cc = this.myAgent.getContainerController();
				
				AgentController ac = cc.acceptNewAgent("Plateia" + this.getAgent().getName(), plateia);
				
				ac.start();
			
				((AgenteParticipante) this.getAgent()).setCompanion(plateia.getAID());
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
	}
}
