public class BattleResult {
		private int rounds;
		private double totalPoints;
		
		public int getRounds() {
			return rounds;
		}
		public void setRounds(int rounds) {
			this.rounds = rounds;
		}
		public double getTotalPoints() {
			return totalPoints;
		}
		public void setTotalPoints(double totalPoints) {
			this.totalPoints = totalPoints;
		}
		
		@Override
		public String toString() {
			return String.format("(%d %.2f)", rounds, totalPoints);
		}
	}