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

			/*Team team = new Team();
			team.setName("TeamA");
			em.persist(team);*/

			Member member = new Member();
			member.setUsername("관리자");
			member.setAge(10);
			member.setType(MemberType.ADMIN);
//			member.setTeam(team);
			em.persist(member);

			Member member2 = new Member();
			member2.setUsername("관리자2");
			member2.setAge(10);
			member2.setType(MemberType.ADMIN);
//			member2.setTeam(team);
			em.persist(member2);

			em.flush();
			em.clear();

//			String query = "select 'a' || 'b' from MemberJ m";
//			String query = "select concat('a', 'b') from MemberJ m";
//			String query = "select substring(m.username, 2, 3) from MemberJ m";
//			String query = "select locate('de', 'abcdef') from MemberJ m";
//			String query = "select size(t.members) from TeamJ t";
			/*@OrderColumn
			String query = "select index(t.members) from TeamJ t";*/
//			String query = "select function('group_concat', m.username) from MemberJ m";
			String query = "select group_concat(m.username) from MemberJ m";
			List<String> result = em.createQuery(query, String.class)
					.getResultList();

			for (String s : result) {
				System.out.println("s = " + s);
			}

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

