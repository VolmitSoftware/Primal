package primal.lang.collection;

import java.util.Collection;

import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

public class GSet<T> extends ObjectOpenHashSet<T>
{
	private static final long serialVersionUID = 1L;

	public GSet()
	{
		super();
	}

	public GSet(Collection<? extends T> c)
	{
		super(c);
	}

	public GSet(int initialCapacity, float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}

	public GSet(int initialCapacity)
	{
		super(initialCapacity);
	}
}
