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
			member.setType(MemberType.ADMIN);
			member.setTeam(team);

			em.persist(member);

			em.flush();
			em.clear();

			/*String query = "select m.username, 'HELLO', true from MemberJ m " +
					"where m.type = jpql.MemberType.USER";*/
			String query = "select m.username, 'HELLO', true from MemberJ m " +
					"where m.type = :userType and m.username is not null";
			List<Object[]> result = em.createQuery(query)
					.setParameter("userType", MemberType.ADMIN)
					.getResultList();

			for (Object[] objects : result) {
				System.out.println("objects[0] = " + objects[0]);
				System.out.println("objects[1] = " + objects[1]);
				System.out.println("objects[2] = " + objects[2]);
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
