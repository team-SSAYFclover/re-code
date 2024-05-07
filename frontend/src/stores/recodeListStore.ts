import { IProblemInfo } from '@/types/model';
import { create } from 'zustand';

interface IRecodeState {
  todayRecodes: IProblemInfo[];
  setTodayRecodes: (newRecodes: IProblemInfo[]) => void;
}

const recodeListStore = create<IRecodeState>((set) => ({
  todayRecodes: [],
  setTodayRecodes: (newRecodes: IProblemInfo[]) => {
    set({
      todayRecodes: newRecodes,
    });
  },
}));

export default recodeListStore;
