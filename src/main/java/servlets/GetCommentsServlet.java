package servlets;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Comment;
import persistence.PersistenceManager;

@Path("comments/get")
public class GetCommentsServlet {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getComments() {
		final PersistenceManager pm = PersistenceManager.getInstance();
		final EntityManagerFactory emf = pm.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c", Comment.class);

		if (query.getResultList().get(0) != null)
//			return query.getResultList().get(0).getContent();
			return "?";
		else
			return "No elements";
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getComment(@PathParam("id") String id) {
		final PersistenceManager pm = PersistenceManager.getInstance();
		final EntityManagerFactory emf = pm.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c WHERE c.id = " + id, Comment.class);
		Comment comment = query.getSingleResult();
		return comment.getContent();
	}
}
