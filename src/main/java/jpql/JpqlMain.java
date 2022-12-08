package jpql;

import javax.persistence.*;
import java.util.Collection;
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

			Member member2 = new Member();
			member2.setUsername("관리자2");
			member2.setAge(10);
			member2.setType(MemberType.ADMIN);
			member2.setTeam(team);
			em.persist(member2);

			em.flush();
			em.clear();

//			String query = "select m.username from MemberJ m"; //상태 필드
//			String query = "select m.team from MemberJ m"; //단일 값 연관 경로(묵시적인 내부 조인), 탐색 o - 웬만해선 배제
//			String query = "select t.members from TeamJ t"; //컬렉션 값 연관 경로, 탐색 x
			String query = "select m from TeamJ t join t.members m"; //명시적 조인
			List<Collection> result = em.createQuery(query, Collection.class)
					.getResultList();

			for (Object o : result) {
				System.out.println("o = " + o);
			}
			/*for (Member s : result) {
				System.out.println("s = " + s);
			}*/

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

