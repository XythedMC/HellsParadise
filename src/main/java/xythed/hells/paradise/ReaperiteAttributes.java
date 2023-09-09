package xythed.hells.paradise;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.UUID;


public class ReaperiteAttributes {
    public static final EntityAttribute GENERIC_JUMP_BOOST = register("generic.jump_boost", new ClampedEntityAttribute("attribute.name.generic_jump_boost", 1.0, 0.0, 2.0).setTracked(true));
    public static final UUID MODIFIER = UUID.fromString("24304803-3329-4779-8258-653119cf3982");

    public static final EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(
            MODIFIER,
            "Jump stick modifier",
            0.75,
            EntityAttributeModifier.Operation.ADDITION
    );
    public static EntityAttribute register(String name, EntityAttribute attribute){
        return Registry.register(Registries.ATTRIBUTE, new Identifier("hellsparadise", name), attribute);
    }

}
