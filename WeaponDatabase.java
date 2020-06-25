public class WeaponDatabase
{
    //1s.p. = 1/10g.p. = 1/50 p.p. = 1 coin
    //1g.p. = 10 coin
    
    /* Table of Contents:
     * 1. Dagger
       2. Club
       3. Hand Axe
       4. Spear
       5. Broadsword
       6. Longsword
       7. Battle Axe
       8. Hammer
       9. Quarterstaff
       10. Shortsword
       11. Footman's Flail
       12. Horseman's Flail
       13. Military Fork
       14. Glaive
       15. Bill-Guisarme
       16. Guisarme
       17. Fauchard
       18. Pike
       19. Crossbow
       20. Bow
       21. Footman's Mace
       22. Horseman's Mace
       23. Morning Star
       24. Partisan
       25. Halberd
       26. Lucern Hamme
       27. Scimitar
       28. Bardiche
       29. Guisarme-Voulge
       30. Bec de Corbin
       31. Glaive-Guisarme
       32. Fauchard-Fork
     */
    public static Weapon getWeaponFromDatabase(int id){
       
       switch(id){
           //Name, Weight, value, medLow, medHigh, largLow, largeHigh
           case 1:
           return new Weapon("Dagger", 10, 20, 1, 4, 1, 3, false);
           case 2:
           return new Weapon("Club", 30, 10, 1, 6, 1, 3, false);
           case 3:
           return new Weapon("Hand Axe", 50, 10, 1, 6, 1, 4, false);
           case 4:
           return new Weapon("Spear", 40, 10, 1, 6, 1, 8, false);
           
           
          //common
           case 5:
           return new Weapon("Broadsword", 75, 100, 2, 8, 2, 7, true);          
           case 6:
           return new Weapon("Longsword", 60, 150, 1, 8, 1, 12, false); 
           case 7:
           return new Weapon("Battle Axe", 75, 50, 1, 8, 1, 8, false);
           case 8:
           return new Weapon("Hammer", 50, 10, 2, 5, 1, 4, false);
           case 9:
           return new Weapon("Quarterstaff", 50, 20, 1, 6, 1, 6, true);
           case 10:
           return new Weapon("Shortsword", 35, 80, 1, 6, 1, 8, false);           
           
           //rare
           case 11:
           return new Weapon("Footman's Flail", 150, 30, 2, 7, 2, 8, false);
           case 12:
           return new Weapon("Horseman's Flail", 35, 80, 2, 5, 2, 5, false);
           case 13:
           return new Weapon("Military Fork", 75, 40, 1, 8, 2, 8, true);
           case 14:
           return new Weapon("Glaive", 75, 60, 1, 6, 1, 10, true);
           case 15:
           return new Weapon("Bill-Guisarme", 150, 60, 2, 8, 1, 10, true);
           case 16:
           return new Weapon("Guisarme", 80, 50, 2, 8, 1, 8, true);
           case 17:
           return new Weapon("Fauchard", 60, 30, 1, 6, 1, 8, true);
           case 18:
           return new Weapon("Pike", 80, 30, 1, 6, 1, 12, true);
           case 19:
           return new Weapon("Crossbow", 80, 160, 2, 5, 2, 7, true);           
           case 20:
           return new Weapon("Bow", 80, 200, 1, 6, 1, 6, true);          
           
           //very rare
           case 21:
           return new Weapon("Footman's Mace", 100, 80, 2, 7, 1, 6, false);
           case 22:
           return new Weapon("Horseman's Mace", 40, 70, 1, 6, 1, 4, false);
           case 23:
           return new Weapon("Morning Star", 125, 50, 2, 8, 2, 7, false);
           case 24:
           return new Weapon("Partisan", 80, 100, 1, 6, 2, 7, true);
           case 25:
           return new Weapon("Halberd", 175, 90, 1, 10, 2, 12, true);
           case 26:
           return new Weapon("Lucern Hammer", 150, 70, 2, 8, 1, 6, true);
           case 27:
           return new Weapon("Scimitar", 40, 15, 1, 8, 1, 8, false);
           case 28:
           return new Weapon("Bardiche", 125, 70, 2, 8, 3, 12, true);
           case 29:
           return new Weapon("Guisarme-Voulge", 150, 70, 2, 8, 2, 8, true);
           case 30:
           return new Weapon("Bec de Corbin", 100, 60, 1, 8, 1, 6, true);
           case 31:
           return new Weapon("Glaive-Guisarme", 100, 100, 2, 8, 2, 12, true);
           case 32:
           return new Weapon("Fauchard-Fork", 80, 80, 1, 8, 1, 10, true);
        }
        return null;
    }

}
