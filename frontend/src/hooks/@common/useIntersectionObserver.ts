import { useEffect, useState } from 'react';
import { InfiniteQueryObserverResult } from '@tanstack/react-query';

interface IuseIntersectionObserverProps {
  threshold?: number;
  hasNextPage: boolean | undefined;
  fetchNextPage: () => Promise<InfiniteQueryObserverResult<any>>;
}

export const useIntersectionObserver = ({
  threshold = 0.1,
  hasNextPage,
  fetchNextPage,
}: IuseIntersectionObserverProps) => {
  const [target, setTarget] = useState<HTMLDivElement | null>(null);

  const observerCallback: IntersectionObserverCallback = (entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting && hasNextPage) {
        fetchNextPage();
      }
    });
  };

  useEffect(() => {
    if (!target) return;

    const observer = new IntersectionObserver(observerCallback, {
      threshold,
    });

    observer.observe(target);

    return () => {
      observer.unobserve(target);
    };
  }, [target, hasNextPage, fetchNextPage]);

  return { setTarget };
};
