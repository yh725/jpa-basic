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

			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);

			/*List<Member> result = em.createQuery("select m from MemberJ as m", Member.class)
					.getResultList();

			Member findMember = result.get(0);
			findMember.setAge(20);*/

//			List<Team> result = em.createQuery("select m.team from MemberJ as m", Team.class)
//					.getResultList();
//			List<Team> result2 = em.createQuery("select t from MemberJ as m join m.team as t", Team.class)
//					.getResultList();

//			List<Address> result = em.createQuery("select o.address from OrderJ o", Address.class)
//					.getResultList();

			/*List resultList = em.createQuery("select distinct m.username, m.age from MemberJ m")
					.getResultList();

			Object o = resultList.get(0);
			Object[] result = (Object[]) o;
			System.out.println("username = " + result[0]);
			System.out.println("age = " + result[1]);*/

			/*List<Object[]> resultList = em.createQuery("select distinct m.username, m.age from MemberJ m")
					.getResultList();

			Object[] result = resultList.get(0);
			System.out.println("username = " + result[0]);
			System.out.println("age = " + result[1]);*/

			List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from MemberJ m", MemberDTO.class)
					.getResultList();

			MemberDTO memberDTO = result.get(0);
			System.out.println("memberDTO = " + memberDTO.getUsername());
			System.out.println("memberDTO = " + memberDTO.getAge());

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
