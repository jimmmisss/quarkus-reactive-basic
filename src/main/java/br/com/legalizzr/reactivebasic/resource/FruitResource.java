package br.com.legalizzr.reactivebasic.resource;

import br.com.legalizzr.reactivebasic.entity.Fruit;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/v1/fruits")
@ApplicationScoped
public class FruitResource {

    @GET
    public Uni<List<Fruit>> getAll() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Uni<Fruit> getSingle(Long id) {
        return Fruit.findById(id);
    }

    @POST
    public Uni<Response> create(Fruit fruit) {
        return Panache.<Fruit>withTransaction(fruit::persist)
                .onItem()
                .transform(inserted -> Response.created(URI.create("/v1/fruit/" + inserted.id))
                        .build());
    }


}
