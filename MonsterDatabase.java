import java.util.Random;
public class MonsterDatabase
{
   static Random random = new Random();
   public static Monster getMonsterFromDatabase(int id){
       //Size, name, hp, defence, xp, medMin, medMax, largeMin, largeMax
     switch(id){
        case 0: return new Monster(CharacterSize.MEDIUM, "Banshee", (random.nextInt(8)+1)*7, 10, 400, 1, 8, 1, 8);
        case 1: return new Monster(CharacterSize.SMALL, "Bat", 1, 3, 1, 1, 1, 1, 1);
        case 2: return new Monster(CharacterSize.MEDIUM, "Large Bat", random.nextInt(4)+1, 3, 3, 1, 2, 1, 4);
        case 3: return new Monster(CharacterSize.MEDIUM, "Black Bear", ((random.nextInt(8)+1)*3)+3, 4, 17, 1, 3, 1, 6);
        case 4: return new Monster(CharacterSize.LARGE, "Brown Bear", ((random.nextInt(8)+1)*5)+5, 5, 42, 1, 6, 1, 8);
        case 5: return new Monster(CharacterSize.LARGE, "Cave Bear", ((random.nextInt(8)+1)*6)+6, 5, 65, 1, 8, 1, 12);
        case 6: return new Monster(CharacterSize.MEDIUM, "Beholder", ((random.nextInt(31)+1)+44), 10, 140, 2, 8, 2, 8);
        case 7: return new Monster(CharacterSize.MEDIUM, "Vulture", random.nextInt(8)+2, 4, 6, 1, 2, 1, 2);
        case 8: return new Monster(CharacterSize.MEDIUM, "Leopard", ((random.nextInt(8)+1)*3)+2, 4, 27, 1, 3, 1, 6);
        case 9: return new Monster(CharacterSize.MEDIUM, "Mountain Lion", ((random.nextInt(8)+1)*3)+1, 4, 27, 1, 3, 1, 6);
        case 10: return new Monster(CharacterSize.LARGE, "Centaur", (random.nextInt(8)+1)*4, 5, 17, 1, 6, 1, 6);
        case 11: return new Monster(CharacterSize.LARGE, "Chimera", (random.nextInt(8)+1)*9, 5, 500, 12, 48, 12, 48);
        case 12: return new Monster(CharacterSize.LARGE, "Crocodile", (random.nextInt(8)+1)*3, 5, 7, 2, 8, 1, 12);
        case 13: return new Monster(CharacterSize.LARGE, "Giant Crab", (random.nextInt(8)+1)*3, 7, 7, 2, 8, 2, 8);
        case 14: return new Monster(CharacterSize.MEDIUM, "Draugr", (random.nextInt(8)+1)*6, 7, 98, 1, 8, 1, 8);
        case 15: return new Monster(CharacterSize.MEDIUM, "Death Knight", (random.nextInt(10)+1)*9, 10, 600, 2, 8, 2, 12);
        case 16: return new Monster(CharacterSize.MEDIUM, "War Dog", ((random.nextInt(8)+1)*2)+2, 4, 7, 2, 8, 2, 8);
        case 17: return new Monster(CharacterSize.LARGE, "Black Dragon", (random.nextInt(8)+1)*12, 9, 700, 3, 12, 9, 54);
        case 18: return new Monster(CharacterSize.LARGE, "Green Dragon", (random.nextInt(8)+1)*13, 10, 800, 3, 24, 6, 60);
        case 19: return new Monster(CharacterSize.LARGE, "White Dragon", (random.nextInt(8)+1)*11, 9, 800, 3, 18, 6, 48);
        case 20: return new Monster(CharacterSize.MEDIUM, "Dryad", (random.nextInt(8)+1)*2, 1, 98, 1, 4, 1, 4);
        case 21: return new Monster(CharacterSize.SMALL, "Hill Dwarf", (random.nextInt(8)+1), 4, 18, 1, 8, 1, 8);
        case 22: return new Monster(CharacterSize.MEDIUM, "Mountain Dwarf", (random.nextInt(8)+1), 4, 27, 1, 8, 1, 8);
        case 23: return new Monster(CharacterSize.LARGE, "Air Elemental", (random.nextInt(8)+1)*12, 8, 700, 2, 20, 2, 20);
        case 24: return new Monster(CharacterSize.LARGE, "Earth Elemental", (random.nextInt(8)+1)*12, 8, 600, 4, 32, 4, 32);
        case 25: return new Monster(CharacterSize.LARGE, "Fire Elemental", (random.nextInt(8)+1)*12, 8, 600, 3, 24, 3, 24);
        case 26: return new Monster(CharacterSize.LARGE, "Water Elemental", (random.nextInt(8)+1)*12, 8, 600, 5, 30, 5, 30);
        case 27: return new Monster(CharacterSize.MEDIUM, "Elf", (random.nextInt(8)+2), 5, 42, 1, 10, 1, 10);
        case 28: return new Monster(CharacterSize.MEDIUM, "Drow", (random.nextInt(8)+1)*2, 6, 65, 2, 8, 2, 8);
        case 29: return new Monster(CharacterSize.LARGE, "Eyewing", (random.nextInt(8)+1)*3, 6, 65, 1, 6, 1, 6);
        case 30: return new Monster(CharacterSize.MEDIUM, "Gargoyle", ((random.nextInt(8)+1)*4)+4, 5, 42, 1, 3, 1, 6);
        case 31: return new Monster(CharacterSize.MEDIUM, "Ghost", (random.nextInt(8)+1)*10, 10, 700, 2, 8, 2, 8);
        case 32: return new Monster(CharacterSize.MEDIUM, "Ghoul", (random.nextInt(8)+1)*2, 4, 18, 1, 3, 1, 6);
        case 33: return new Monster(CharacterSize.MEDIUM, "Ghast", (random.nextInt(8)+1)*4, 6, 65, 1, 4, 1, 8);
        case 34: return new Monster(CharacterSize.LARGE, "Cyclops", (random.nextInt(8)+1)*13, 8, 400, 6, 36, 6, 36);
        case 35: return new Monster(CharacterSize.LARGE, "Ettin", (random.nextInt(8)+1)*10, 7, 300, 1, 10, 2, 12);
        case 36: return new Monster(CharacterSize.LARGE, "Frost Giant", ((random.nextInt(8)+1)*14)+2, 10, 400, 1, 8, 1, 8);
        case 37: return new Monster(CharacterSize.LARGE, "Hill Giant", ((random.nextInt(8)+1)*12 + 2), 7, 200, 1, 6, 1, 6);
        case 38: return new Monster(CharacterSize.LARGE, "Mountain Giant", ((random.nextInt(8)+1)*15)+3, 6, 300, 1, 8, 1, 8);
        case 39: return new Monster(CharacterSize.MEDIUM, "Gibberling", (random.nextInt(8)+1), 0, 4, 1, 8, 1, 8);
        case 40: return new Monster(CharacterSize.LARGE, "Gnoll", (random.nextInt(8)+1)*2, 5, 4, 2, 8, 2, 8);
        case 41: return new Monster(CharacterSize.SMALL, "Spriggan", ((random.nextInt(8)+1)*4)+4, 7, 300, 2, 8, 2, 8);
        case 42: return new Monster(CharacterSize.SMALL, "Goblin", Math.max((random.nextInt(8)+1)-1, 1), 4, 2, 1, 6, 1, 6);
        case 43: return new Monster(CharacterSize.LARGE, "Golem", Math.min((random.nextInt(8)+1)*14, 60), 5, 800, 3, 24, 3, 24);
        case 44: return new Monster(CharacterSize.LARGE, "Gorgon", (random.nextInt(8)+1)*8, 8, 140, 2, 12, 2, 12);
        case 45: return new Monster(CharacterSize.SMALL, "Gremlin", (random.nextInt(8)+1)*4, 6, 65, 1, 4, 1, 4);
        case 46: return new Monster(CharacterSize.LARGE, "Griffon", (random.nextInt(8)+1)*7, 7, 65, 1, 4, 2, 16);
        case 47: return new Monster(CharacterSize.MEDIUM, "Grimlock", (random.nextInt(8)+1)*2, 5, 4, 1, 6, 1, 6);
        case 48: return new Monster(CharacterSize.MEDIUM, "Hag", ((random.nextInt(8)+1)*7)+7, 10, 400, 9, 16, 3, 9);
        case 49: return new Monster(CharacterSize.MEDIUM, "Harpy", (random.nextInt(8)+1)*7, 3, 98, 1, 3, 1, 6);
        case 50: return new Monster(CharacterSize.MEDIUM, "Haunt", (random.nextInt(8)+1)*5, 10, 200, 2, 8, 2, 8);
        case 51: return new Monster(CharacterSize.MEDIUM, "Hellhound", (random.nextInt(8)+1)*5, 6, 65, 1, 10, 1, 10);
        case 52: return new Monster(CharacterSize.MEDIUM, "Hobgoblin", (random.nextInt(8)+1)+1, 5, 4, 2, 8, 2, 8);
        case 53: return new Monster(CharacterSize.LARGE, "Hydra", (random.nextInt(8)+1)*7, 5, 200, 6, 36, 6, 36);
        case 54: return new Monster(CharacterSize.SMALL, "Imp", ((random.nextInt(8)+1)*2)+8, 8, 140, 1, 4, 1, 4);
        case 55: return new Monster(CharacterSize.SMALL, "Kobald", (random.nextInt(8)+1)/2, 3, 2, 1, 4, 1, 6);
        case 56: return new Monster(CharacterSize.MEDIUM, "Lizard Man", ((random.nextInt(8)+1)*2)+1, 5, 7, 1, 2, 1, 6);
        case 57: return new Monster(CharacterSize.MEDIUM, "Lycanthrope", ((random.nextInt(8)+1)*2)+2, 4, 12, 1, 2, 1, 4);
        case 58: return new Monster(CharacterSize.LARGE, "Manticore", ((random.nextInt(8)+1)*6)+3, 6, 98, 3, 9, 3, 24);
        case 59: return new Monster(CharacterSize.LARGE, "Minotaur", ((random.nextInt(8)+1)*6)+3, 4, 140, 2, 8, 2, 8);
        case 60: return new Monster(CharacterSize.MEDIUM, "Mummy", ((random.nextInt(8)+1)*6)+3, 7, 300, 1, 12, 1, 12);
        case 61: return new Monster(CharacterSize.LARGE, "Ogre", ((random.nextInt(8)+1)*4)+1, 5, 27, 1, 10, 1, 10);
        case 62: return new Monster(CharacterSize.MEDIUM, "Orc", (random.nextInt(8)+1), 4, 2, 1, 8, 1, 8);
        case 63: return new Monster(CharacterSize.MEDIUM, "Deadly Black Pudding", (random.nextInt(8)+1)*10, 4, 200, 3, 24, 3, 24);
        case 64: return new Monster(CharacterSize.MEDIUM, "Poltergeist", 4, 0, 12, 1, 4, 1, 4);
        case 65: return new Monster(CharacterSize.MEDIUM, "Satyr", (random.nextInt(8)+1)*5, 5, 98, 2, 8, 2, 8);
        case 66: return new Monster(CharacterSize.SMALL, "Scorpion", (random.nextInt(8)+1)*9, 5, 18, 1, 4, 1, 4);
        case 67: return new Monster(CharacterSize.MEDIUM, "Shadow", ((random.nextInt(8)+1)*3)+3, 3, 42, 2, 5, 2, 5);
        case 68: return new Monster(CharacterSize.MEDIUM, "Skeleton", (random.nextInt(8)+1), 3, 7, 1, 6, 1, 6);
        case 69: return new Monster(CharacterSize.LARGE, "Giant Slug", (random.nextInt(8)+1)*12, 2, 500, 1, 12, 1, 12);
        case 70: return new Monster(CharacterSize.MEDIUM, "Spectre", ((random.nextInt(8)+1)*7)+3, 8, 300, 1, 8, 1, 8);
        case 71: return new Monster(CharacterSize.LARGE, "Sphinx", (random.nextInt(8)+1)*12, 12, 700, 4, 24, 4, 24);
        case 72: return new Monster(CharacterSize.LARGE, "Tarrasque", 300, 13, 10700, 6, 72, 6, 72);
        case 73: return new Monster(CharacterSize.LARGE, "Titan", (random.nextInt(8)+1)*20, 10, 2100, 14, 84, 14, 84);
        case 74: return new Monster(CharacterSize.MEDIUM, "Troglodyte", (random.nextInt(8)+1)*2, 5, 12, 1, 2, 2, 8);
        case 75: return new Monster(CharacterSize.LARGE, "Troll", ((random.nextInt(8)+1)*6)+6, 6, 140, 15, 24, 15, 24);
        case 76: return new Monster(CharacterSize.MEDIUM, "Vampire", ((random.nextInt(8)+1)*8)+3, 9, 800, 5, 10, 5, 10);
        case 77: return new Monster(CharacterSize.MEDIUM, "Wight", ((random.nextInt(8)+1)*4)+3, 5, 140, 1, 4, 1, 4);
        case 78: return new Monster(CharacterSize.SMALL, "Wolf", (random.nextInt(8)+1)*3, 3, 12, 2, 5, 2, 5);
        case 79: return new Monster(CharacterSize.MEDIUM, "Wraith", ((random.nextInt(8)+1)*5)+3, 6, 200, 1, 6, 1, 6);
        case 80: return new Monster(CharacterSize.LARGE, "Wyvern", ((random.nextInt(8)+1)*7)+7, 7, 140, 2, 16, 1, 6);
        case 81: return new Monster(CharacterSize.LARGE, "Yeti", ((random.nextInt(8)+1)*4)+4, 4, 42, 2, 12, 2, 12);
        case 82: return new Monster(CharacterSize.MEDIUM, "Zombie", (random.nextInt(8)+1)*2, 2, 7, 1, 8, 1, 8);
    }
    return null;
    }
}
