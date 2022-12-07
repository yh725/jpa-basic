package jpql;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("TeamA");
			member.setAge(10);
			member.setTeam(team);

			em.persist(member);

			em.flush();
			em.clear();

			/*String query = "select m from MemberJ m, TeamJ t where m.username = t.name";
			List<Member> result = em.createQuery(query, Member.class)
					.getResultList();

			System.out.println("result = " + result.size());*/

			/*String query = "select m from MemberJ m left join m.team t on t.name = 'TeamA'";
			List<Member> result = em.createQuery(query, Member.class)
					.getResultList();

			System.out.println("result = " + result.size());*/

			String query = "select m from MemberJ m left join Team t on m.username = t.name";
			List<Member> result = em.createQuery(query, Member.class)
					.getResultList();

			System.out.println("result = " + result.size());

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();

	}
}
