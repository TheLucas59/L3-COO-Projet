package main.java.fr.ulille.l3.competitions;

import java.util.ArrayList;
import java.util.List;

import main.java.fr.ulille.l3.excpetions.CompetitorsNumberNotPowerOf2Exception;
import main.java.fr.ulille.l3.excpetions.EmptyCompetitorsListException;
import main.java.fr.ulille.l3.modele.Competitor;

public class Tournament extends Competition {

	public Tournament(List<Competitor> competitors) throws NullPointerException, EmptyCompetitorsListException, CompetitorsNumberNotPowerOf2Exception {
		super(competitors);
		checkModulo2(competitors);
	}

	/**
	 * Check if the list of competitors contains modulo 2 of competitors. It is needed to check if a Tournament is possible
	 * @param competitors The list of competitors to be checked
	 * @throws CompetitorsNumberNotPowerOf2 Exception if modulo 2 is not respected
	 */
	private void checkModulo2(List<Competitor> competitors) throws CompetitorsNumberNotPowerOf2Exception {
		int checkIfPowerOfTwo = competitors.size();
		while(checkIfPowerOfTwo > 1) {
			if(checkIfPowerOfTwo%2 != 0){
				throw new CompetitorsNumberNotPowerOf2Exception();
			}
			checkIfPowerOfTwo = checkIfPowerOfTwo/2;
		}

	}

	/**
	 * Play a match following the rules of a Tournament, two competitors goes against each other, the loser is eliminated and the winner will play against the others winners of the round.
	 * @param competitors The list of competitors that will compete in this tournament
	 */
	@Override
	protected void play(List<Competitor> competitors) {
		List<Competitor> remainingCompetitors = new ArrayList<Competitor>(competitors);
		while(remainingCompetitors.size() > 1) {
			Competitor c1 = remainingCompetitors.get(0);
			Competitor c2 = remainingCompetitors.get(1);
			Competitor winner = playMatch(c1,c2);
			removeLooserFromCompetitors(remainingCompetitors, c1, c2, winner);
		}
	}

	private void removeLooserFromCompetitors(List<Competitor> remainingCompetitors, Competitor c1, Competitor c2, Competitor winner) {
		if(winner.equals(c1)) {
			remainingCompetitors.remove(c2);
			if(remainingCompetitors.size() != 2) {
				remainingCompetitors.add(c1);
				remainingCompetitors.remove(0);
			}
		} 
		else {
			remainingCompetitors.remove(c1);
			if(remainingCompetitors.size() != 2) {
				remainingCompetitors.add(c2);
				remainingCompetitors.remove(0);
			}
		}
	}



}

