import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TeamCompositionReader {
	
	private void addTeam(Map<String, Collection<String>> teams, String robot, String team) {
		if (teams.containsKey(robot)) {
			teams.get(robot).add(team);
		}else {
			ArrayList<String> teamList = new ArrayList<>();
			teamList.add(team);
			teams.put(robot, teamList);
		}
	}
	
	public Map<String, Collection<String>> readFile(String fileName) throws IOException{
		Map<String, Collection<String>> teams = new HashMap<>();
		
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		try {
			String line = reader.readLine();
			while (line != null) {
				String[] ss = line.split(";");
				for (int i = 1; i < ss.length; i++) {
					addTeam(teams, ss[i], ss[0]);
				}
				line = reader.readLine();
			}
		} finally {
			reader.close();
		}
		return teams;
	}
}
