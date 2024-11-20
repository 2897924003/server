package ss.user.atcl;

public interface DomainObjectConverter<S, D> {
    D convert(S source);

}

