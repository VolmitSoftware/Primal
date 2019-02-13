package primal.bukkit.nms;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import primal.lang.collection.GMap;

public class PacketCache<T>
{
	private static GMap<Class<?>, PacketCache<?>> cache;
	private final T t;

	public PacketCache(T cached)
	{
		t = cached;
	}

	public synchronized T take()
	{
		return t;
	}

	@SuppressWarnings("unchecked")
	public static synchronized <T> T take(Class<? extends T> c)
	{
		if(!cache.containsKey(c))
		{
			try
			{
				cache.put(c, new PacketCache<T>((T) c.getConstructor().newInstance()));
			}

			catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
			{
				e.printStackTrace();
				return null;
			}
		}

		return (T) cache.get(c).take();
	}

	public static synchronized <T> T take(Class<? extends T> c, Supplier<T> sup)
	{
		if(!cache.containsKey(c))
		{
			cache.put(c, new PacketCache<T>(sup.get()));
		}

		return take(c);
	}

	public static void reset()
	{
		cache = new GMap<>();
	}
}
