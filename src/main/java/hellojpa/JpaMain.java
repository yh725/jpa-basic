package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*Member member = new Member();
            member.setId(3L);
            member.setName("HelloB");*/

            /*Member findMember = em.find(Member.class, 2L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            findMember.setName("HelloJPA");*/

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(5)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

//            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
