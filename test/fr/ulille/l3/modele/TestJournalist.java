package fr.ulille.l3.modele;

import fr.ulille.l3.util.CompetitionObserver;

public class TestJournalist extends TestObservers{

	@Override
	protected CompetitionObserver createObserver() {
		return new Journalist(null);
	}

}
