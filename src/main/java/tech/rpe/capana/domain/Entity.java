package tech.rpe.capana.domain;

import java.util.Objects;

import tech.rpe.capana.domain.validation.ValidationHandler;

public abstract class Entity<I extends Identifier> {

    protected I id;

    protected Entity(final I id) {
        this.id = id;
    }

    public abstract void validate(ValidationHandler handler);

    public I getId() {
        return id;
    }

    public void setId(final I id) {
        Objects.requireNonNull(id, "'id' should not be null");
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Entity<?> entity = (Entity<?>) o;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
