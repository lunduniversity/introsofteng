import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BestBot { 
	
	public static void main(String[] args) {
		Map<String, Collection<BattleResult>> teamResults = null;
		Map<String, Collection<String>> teams = null;
		try {
			teamResults = new BattleResultReader().readFiles(Arrays.copyOfRange(args, 1, args.length));
			teams = new TeamCompositionReader().readFile(args[0]);
			
			Map<String, Double> avgScores = new HashMap<>();
			for (String robot : teams.keySet()) {
				double points = 0;
				int nbrRounds = 0;
				for (String team : teams.get(robot)) {
					if (!teamResults.containsKey(team)) continue;
					for (BattleResult br : teamResults.get(team)) {
						points += br.getTotalPoints();
						nbrRounds += br.getRounds();
					}
				}
				double avgPoint = points / nbrRounds;
				avgScores.put(robot, avgPoint);
			}
			
			ArrayList<Entry<String,Double>> list = new ArrayList<>(avgScores.entrySet());
			list.sort(Entry.comparingByValue());
			for (int i = list.size() - 1; i >= 0; i--) {
				System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static <T> void printMap(Map<String, Collection<T>> map) {
		for (String name : map.keySet()) {
			System.out.println(name);
			for (T res : map.get(name)) {
				System.out.println("\t" + res);
			}
		}
	}
}
