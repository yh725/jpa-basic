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
			member.setUsername("관리자");
			member.setAge(10);
			member.setType(MemberType.ADMIN);
			member.setTeam(team);

			em.persist(member);

			em.flush();
			em.clear();

			/*String query =
					"select " +
							"case when m.age <= 10 then '학생요금' " +
							"     when m.age >= 60 then '경로요금' " +
							"     else '일반요금' " +
							"end " +
					"from MemberJ m";*/
//			String query = "select coalesce(m.username, '이름 없는 회원') as username from MemberJ m";
			String query = "select NULLIF(m.username, '관리자') from MemberJ m";
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
