package servlets;

import java.time.Instant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import entities.Comment;
import persistence.PersistenceManager;

@Path("/comments/add")
public class AddCommentServlet {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("{comment}")
	public void addComment(@PathParam("comment") String content) {
		final PersistenceManager pm = PersistenceManager.getInstance();
		final EntityManagerFactory emf = pm.getEntityManagerFactory();
		final EntityManager em = emf.createEntityManager();
		Comment comment = new Comment(content, Instant.now());
		final EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.persist(comment);
		em.flush();

		transaction.commit();
	}

}
