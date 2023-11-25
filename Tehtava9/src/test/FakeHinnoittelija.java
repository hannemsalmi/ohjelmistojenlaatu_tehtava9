package test;
import ohjelma.Asiakas;
import ohjelma.IHinnoittelija;
import ohjelma.Tuote; 

public class FakeHinnoittelija implements IHinnoittelija {
	private float alennus;

	public FakeHinnoittelija(float alennus) {
		this.alennus = alennus;
	}

	public float getAlennusProsentti(Asiakas asiakas, Tuote tuote) {
		return alennus;
	}

	@Override
	public void setAlennusProsentti(Asiakas asiakas, float f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aloita() {
		// TODO Auto-generated method stub
		
	}
}
