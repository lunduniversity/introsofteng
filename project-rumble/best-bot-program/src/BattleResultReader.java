import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BattleResultReader {
	
	private String newLine;
	
	public BattleResultReader() {
		newLine = System.getProperty("line.separator");
	}
	
	public Map<String, Collection<BattleResult>> readFiles(String[] fileNames) throws IOException {
		Map<String, Collection<BattleResult>> teamResults  = new HashMap<>();
		for (int i = 0; i < fileNames.length; i++) {
			readFile(teamResults, fileNames[i]);
		}
		return teamResults;
	}
	
	public void readFile(Map<String, Collection<BattleResult>> teamResults, String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line;
			ArrayList<String> battles = new ArrayList<>();
			while ((line =  reader.readLine()) != null) {
				if (line.startsWith("$")) {
					battles.add(sb.toString());
					sb = new StringBuilder();
				}else {
					sb.append(line + newLine);
				}
			}
			for (String battleText : battles) {
				parseBattle(teamResults, battleText);
			}
		} finally {
			reader.close();
		}
	}
	
	public void parseBattle(Map<String, Collection<BattleResult>> teamResults, String battleText) {
		String[] lines = battleText.split(newLine);
		int nRounds = Integer.parseInt(lines[1].replaceAll("[\\D]", ""));
		for (int i = 3; i < lines.length; i++) {
			String[] fields = lines[i].split(",");
			String teamName = fields[1];
			String scoreText = fields[2];
			Scanner sc = new Scanner(scoreText);
			int score = sc.nextInt();
			
			BattleResult result = new BattleResult();
			result.setRounds(nRounds);
			result.setTotalPoints(score);
			addBattleResult(teamResults, teamName, result);
		}
	}
	
	private void addBattleResult(Map<String, Collection<BattleResult>> teamResults, String teamName, BattleResult result) {
		if (teamResults.containsKey(teamName)) {
			teamResults.get(teamName).add(result);
		}else {
			ArrayList<BattleResult> list = new ArrayList<>();
			list.add(result);
			teamResults.put(teamName, list);
		}
	}
		
}
