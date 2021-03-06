package by.matveev.rorty;

import by.matveev.rorty.entities.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class EntityContactResolver implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        final Entity entityA = entityA(contact);
        final Entity entityB = entityB(contact);

        if (entityA != null & entityB != null) {
            entityA.onContactStart(entityB);
            entityB.onContactStart(entityA);
        }

    }

    @Override
    public void endContact(Contact contact) {
        final Entity entityA = entityA(contact);
        final Entity entityB = entityB(contact);

        if (entityA != null & entityB != null) {
            entityA.onContactEnd(entityB);
            entityB.onContactEnd(entityA);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // do nothing
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // do nothing
    }

    private static Entity entityA(Contact contact) {
        final Object dataA = contact.getFixtureA().getBody().getUserData();
        return dataA instanceof Entity ? (Entity) dataA : null;
    }

    private static Entity entityB(Contact contact) {
        final Object dataB = contact.getFixtureB().getBody().getUserData();
        return dataB instanceof Entity ? (Entity) dataB : null;
    }

}
