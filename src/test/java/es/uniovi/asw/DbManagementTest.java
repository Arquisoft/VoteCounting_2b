package es.uniovi.asw;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.dbManagement.model.*;
import es.uniovi.asw.dbManagement.model.types.TurnoutKey;
import es.uniovi.asw.dbManagement.model.types.VoteKey;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.dbManagement.persistence.RepositoryConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class, RepositoryConfiguration.class})
public class DbManagementTest {
	
	private static boolean first = true;
	
	private static Referendum referendum = new Referendum();
	private static ClosedList closedList = new ClosedList();
	private static OpenList openList = new OpenList();
	
	private static List<Candidature> candidatures = new ArrayList<>();
	private static List<Candidate> candidates = new ArrayList<>();
	
	private static List<Voter> voters = new ArrayList<Voter>();
	private static List<PollingPlace> pollingPlaces = new ArrayList<>();
	private static List<Region> regions = new ArrayList<>();
	private static List<Constituency> contituencies = new ArrayList<>();
	
	@Before
	public void before() {
		
		if (first) {
			first = false;
		Calendar c = Calendar.getInstance();
		
		referendum.setName("Referendum");
		referendum.setStartDate(c.getTime());
		c.add(Calendar.DATE, 2);
		referendum.setExpiryDate(c.getTime());
		referendum.setQuestion("Question");
		
		Repository.electionR.save(referendum);
		
		closedList.setName("ClosedList");
		closedList.setStartDate(c.getTime());
		c.add(Calendar.DATE, 2);
		closedList.setExpiryDate(c.getTime());
		
		Repository.electionR.save(closedList);
		
		openList.setName("OpenList");
		openList.setStartDate(c.getTime());
		c.add(Calendar.DATE, 2);
		openList.setExpiryDate(c.getTime());
		openList.setNumChoices(3);
		
		Repository.electionR.save(openList);
		
		Region region;
		Constituency constituency;
		PollingPlace pollingPlace;
		
		int cnt = 0;
		long p = 0;
		for (int i = 0; i < 17; i++) {
			region = new Region();
			region.setName("Region" + i);
			
			Repository.regionR.save(region);
			regions.add(region);
			
			for (int j = 0; j < 3; j++) {
				constituency = new Constituency();
				constituency.setName("Constituency" + cnt);
				cnt++;
				constituency.setRegion(region);
				
				Repository.constituencyR.save(constituency);
				contituencies.add(constituency);
				
				for (int k = 0; k < 10; k++) {
					pollingPlace = new PollingPlace();
					pollingPlace.setId(p);
					p++;
					pollingPlace.setConstituency(constituency);
					Repository.pollingPlaceR.save(pollingPlace);
					pollingPlaces.add(pollingPlace);
				}
				
			}
			
		}
		
		Voter voter;
		
		for (int i = 0; i < 1530; i++) {
			voter = new Voter("Name" + i, "Nif" + i, "email" + i);
			voter.setPassword("password" + i);
			voter.setPollingPlace(pollingPlaces.get(i%pollingPlaces.size()));
			Repository.voterR.save(voter);
			voters.add(voter);
		}
		
		Candidature candidature;
		Candidate candidate;
		int cand = 0;
		for (int i = 0; i < 8; i++) {
			candidature = new Candidature();
			candidature.setName("Candidature" + i);
			candidature.setInitial("C" + i);
			candidature.addElection(closedList);
			Repository.candidatureR.save(candidature);
			candidatures.add(candidature);
			
			for (int j = 0; j < 5; j++) {
				candidate = new Candidate();
				candidate.setName("Name" + cand);
				candidate.setSurname("Surname" + cand);
				candidate.setDNI("nif" + cand);
				cand++;
				candidate.setCandidature(candidature);
				candidate.addElection(openList);
				Repository.candidateR.save(candidate);
				candidates.add(candidate);
			}
		}
		}
		
	}
	
	@Test
	public void testCandidature() {
		Repository.electionR.save(closedList);
		List<Candidature> candidatures = Repository.candidatureR.findByElection(closedList);
	
		assertArrayEquals(DbManagementTest.candidatures.toArray(), candidatures.toArray());
		assertTrue(candidatures.size() > 0);
		for (Candidature c:candidatures) {
			assertTrue(c.getElections().contains(closedList));
			assertFalse(c.getCandidates().isEmpty());
		}
		
		assertNotNull(Repository.candidatureR.findByName("Candidature0"));
		assertNotNull(Repository.candidatureR.findByNameAndElection("Candidature1", closedList));
	}
	
	@Test
	public void testCandidate() {
		Repository.electionR.save(openList);
		List<Candidate> candidates = Repository.candidateR.findByElection(openList);
		
		assertArrayEquals(DbManagementTest.candidates.toArray(), candidates.toArray());
		assertTrue(candidates.size() > 0);
		for (Candidate c:candidates) {
			assertTrue(c.getElections().contains(openList));
			assertNotNull(c.getCandidature());
		}
		
		
		for (Candidature c:DbManagementTest.candidatures) {
			candidates = Repository.candidateR.findByCandidature(c);
			for (Candidate can:candidates)
				assertTrue(c.getCandidates().contains(can));
		}
		
	}
	
	@Test
	public void testConstituency() {
		Constituency constituency;
		for (Constituency c:DbManagementTest.contituencies) {
			constituency = Repository.constituencyR.findByName(c.getName());
			assertNotNull(constituency);
			assertEquals(c.getRegion(), constituency.getRegion());
			assertFalse(c.getPollingPlaces().isEmpty());
			for (PollingPlace p:c.getPollingPlaces())
				assertTrue(constituency.getPollingPlaces().contains(p));
		}
	}
	
	@Test
	public void testPollingPlace() {
		PollingPlace pollingPlace;
		for (PollingPlace p:DbManagementTest.pollingPlaces) {
			pollingPlace = Repository.pollingPlaceR.findOne(p.getId());
			assertNotNull(pollingPlace);
			assertEquals(p.getConstituency(), pollingPlace.getConstituency());
		}
	}
	
	@Test
	public void testRegion() {
		Region region;
		for(Region r:DbManagementTest.regions) {
			region =  Repository.regionR.findByName(r.getName());
			assertNotNull(region);
			assertEquals(r, region);
			for (Constituency c:r.getConstituencies())
				assertTrue(region.getConstituencies().contains(c));
		}
	}
	
	@Test
	public void testElection() {
		assertEquals(referendum, Repository.electionR.findOne(referendum.getId()));
		assertEquals(referendum, Repository.electionR.findByName(referendum.getName()));
		assertEquals(referendum, Repository.electionR.findActual());
		assertEquals(1, referendum.getNumChoices());
		
		assertEquals(closedList, Repository.electionR.findOne(closedList.getId()));
		assertEquals(closedList, Repository.electionR.findByName(closedList.getName()));
		assertFalse(closedList.equals(Repository.electionR.findActual()));
		assertEquals(1, closedList.getNumChoices());
		
		assertEquals(openList, Repository.electionR.findOne(openList.getId()));
		assertEquals(openList, Repository.electionR.findByName(openList.getName()));
		assertFalse(openList.equals(Repository.electionR.findActual()));
		assertEquals(3, openList.getNumChoices());
	}
	
	@Test
	public void testVoteReferendum() {
		VoteReferendum vote = null;
		Turnout turnout;
		Voter voter;
		for (int i = 0; i < 3*10; i = i+3) {
			voter = voters.get(i);
			vote = Repository.voteR.findVoteReferendumByElectionAndPollingPlace(referendum, voter.getPollingPlace());
			if (vote == null) {
				vote = new VoteReferendum();
				vote.setElection(referendum);
				vote.setPollingPlace(voter.getPollingPlace());
			}
			vote.increaseYeses();
			Repository.voteR.save(vote);
			
			turnout = new Turnout(referendum, voter);
			Repository.turnoutR.merge(turnout);
		}
		
		referendum = (Referendum) Repository.electionR.findOne(referendum.getId());
		assertTrue(referendum.getTurnout().size() > 0);
		assertTrue(referendum.getVotes().size() > 0);
		assertTrue(referendum.getVotes().contains(vote));
		
	}
	
	@Test
	public void testVoteClosedList() {
		VoteClosedList vote = null;
		Turnout turnout;
		Voter voter;
		
		for (int i = 1; i < 3*10; i = i+3) {
			voter = voters.get(i);
			vote = Repository.voteR.findByElectionAndCandidatureAndPollingPlace(closedList, candidatures.get(0), voter.getPollingPlace());
			if (vote == null) {
				vote = new VoteClosedList();
				vote.setElection(closedList);
				vote.setCandidature(candidatures.get(0));
				vote.setPollingPlace(voter.getPollingPlace());
			}
			vote.setNumVotes(vote.getNumVotes() + 1);
			Repository.voteR.save(vote);
			
			turnout = new Turnout(closedList, voter);
			Repository.turnoutR.merge(turnout);
		}
		
		closedList = (ClosedList) Repository.electionR.findOne(closedList.getId());
		assertTrue(closedList.getTurnout().size() > 0);
		assertTrue(closedList.getVotes().size() > 0);
		assertTrue(closedList.getVotes().contains(vote));
		
	}
	
	@Test
	public void testVoteOpenList() {
		VoteOpenList vote = null;
		Turnout turnout;
		Voter voter;
		
		for (int i = 1; i < 3*10; i = i+3) {
			voter = voters.get(i);
			vote = Repository.voteR.findByElectionAndCandidateAndPollingPlace(openList, candidates.get(0), voter.getPollingPlace());
			if (vote == null) {
				vote = new VoteOpenList();
				vote.setElection(openList);
				vote.setCandidate(candidates.get(0));
				vote.setPollingPlace(voter.getPollingPlace());
			}
			vote.setNumVotes(vote.getNumVotes() + 1);
			Repository.voteR.save(vote);
			
			turnout = new Turnout(openList, voter);
			Repository.turnoutR.merge(turnout);
		}
		
		openList = (OpenList) Repository.electionR.findOne(openList.getId());
		assertTrue(openList.getTurnout().size() > 0);
		assertTrue(openList.getVotes().size() > 0);
		assertTrue(openList.getVotes().contains(vote));
	}
	
	@Test
    public void testIntegration() {

        Candidature candidature = new Candidature();
        candidature.setName("Izquierda Unida");
        candidature.setInitial("IU");
        candidature.setDescription("Candidatura electoral de izquierda unida");

        Candidate candidate = new Candidate();

        candidate.setName("Alberto");
        candidate.setDNI("67890976E");
        candidate.setSurname("Garzon");
        candidate.setCandidature(candidature);
        candidate.setCandidature(candidature);

        Repository.candidatureR.save(candidature);

        Candidature candidature1 = Repository.candidatureR.findByName("Izquierda Unida");

        assertEquals("MismaCandidaturas",candidature,candidature1);

    }
	
	@Test
	 public void testUser() {

		
        User user1 = new User("Borja", "12345678R", "borja@gmail.com", 1, "borja");
        User user2 = new User("Nauce", "12345678R", "borja@gmail.com", 1, "borja");
        User user3 = new User("Nauce", "12345678T", "nauce@gmail.com", 1, "borja");
        User user4 = new User("Nauce", "12345678T", "nauce@gmail.com", 2, "borja");
        User user5 = new User("Nauce", "12345678T", "nauce@gmail.com", 2, "nauce");

        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
        assertFalse(user1.equals(user4));
        assertFalse(user1.equals(user5));
        
        assertTrue(user2.equals(user1));
        assertFalse(user2.equals(user3));
        assertFalse(user2.equals(user4));
        assertFalse(user2.equals(user5));
        
        assertFalse(user3.equals(user1));
        assertFalse(user3.equals(user2));
        assertTrue(user3.equals(user4));
        assertFalse(user3.equals(user5));
        
        assertFalse(user4.equals(user1));
        assertFalse(user4.equals(user2));
        assertTrue(user4.equals(user3));
        assertFalse(user4.equals(user5));
        
        assertFalse(user5.equals(user1));
        assertFalse(user5.equals(user2));
        assertFalse(user5.equals(user3));
        assertFalse(user5.equals(user4));
        

    }
	@Test
 public void testTurnOut() {

		ClosedList election1=new ClosedList();
		OpenList election2=new OpenList();
		
		Voter voter1=new Voter("Sergio", "45678913H", "sergio@gmail.com");
		Voter voter2=new Voter("Marcelo", "36925816L", "marcelo@gmail.com");
		Voter voter3=new Voter("Nacho", "95135746P", "nacho@gmail.com");
		
		Turnout turn1= new Turnout(election1, voter1);
		Turnout turn2= new Turnout(election1, voter2);
		Turnout turn3= new Turnout(election1, voter3);
		Turnout turn4= new Turnout(election2, voter1);
		Turnout turn5= new Turnout(election2, voter2);
		Turnout turn6= new Turnout(election2, voter3);
        

		assertTrue(turn1.equals(turn1));
		assertTrue(turn2.equals(turn2));
		assertTrue(turn3.equals(turn3));
		assertTrue(turn4.equals(turn4));
		assertTrue(turn5.equals(turn5));
		assertTrue(turn6.equals(turn6));
		
        assertFalse(turn1.equals(turn2));
        assertFalse(turn1.equals(turn3));
        assertFalse(turn1.equals(turn4));
        assertFalse(turn1.equals(turn5));
        assertFalse(turn1.equals(turn6));
        
        assertFalse(turn2.equals(turn1));
        assertFalse(turn2.equals(turn3));
        assertFalse(turn2.equals(turn4));
        assertFalse(turn2.equals(turn5));
        assertFalse(turn2.equals(turn6));
        
        assertFalse(turn3.equals(turn1));
        assertFalse(turn3.equals(turn2));
        assertFalse(turn3.equals(turn4));
        assertFalse(turn3.equals(turn5));
        assertFalse(turn3.equals(turn6));
        
        assertFalse(turn4.equals(turn1));
        assertFalse(turn4.equals(turn2));
        assertFalse(turn4.equals(turn3));
        assertFalse(turn4.equals(turn5));
        assertFalse(turn4.equals(turn6));
        
        assertFalse(turn5.equals(turn1));
        assertFalse(turn5.equals(turn2));
        assertFalse(turn5.equals(turn3));
        assertFalse(turn5.equals(turn4));
        assertFalse(turn5.equals(turn6));
        
        assertFalse(turn6.equals(turn1));
        assertFalse(turn6.equals(turn2));
        assertFalse(turn6.equals(turn3));
        assertFalse(turn6.equals(turn4));
        assertFalse(turn6.equals(turn5));
    }
}