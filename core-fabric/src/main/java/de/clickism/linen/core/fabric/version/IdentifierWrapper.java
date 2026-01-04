/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric.version;

//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//?} else {
/*import net.minecraft.resources.ResourceLocation;
 *///?}
import org.jetbrains.annotations.Nullable;

/**
 * Wrapper class for Minecraft Identifier to provide version-independent access.
 */
public class IdentifierWrapper {
    private final String namespace;
    private final String path;

    /**
     * Constructor for IdentifierWrapper.
     *
     * @param namespace Identifier namespace
     * @param path      Identifier path
     */
    protected IdentifierWrapper(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    /**
     * Wraps a Minecraft Identifier into an IdentifierWrapper.
     *
     * @param identifier the Minecraft Identifier
     * @return the wrapped IdentifierWrapper
     */
    public static IdentifierWrapper wrap(
            //? if >=1.21.11 {
            Identifier identifier
            //?} else {
            /*ResourceLocation identifier
             *///?}
    ) {
        return new IdentifierWrapper(identifier.getNamespace(), identifier.getPath());
    }

    /**
     * Creates an IdentifierWrapper from namespace and path.
     *
     * @param namespace the namespace
     * @param path      the path
     * @return the created IdentifierWrapper
     */
    public static IdentifierWrapper of(String namespace, String path) {
        return new IdentifierWrapper(namespace, path);
    }

    /**
     * Creates an IdentifierWrapper with the default "minecraft" namespace.
     *
     * @param path the path
     * @return the created IdentifierWrapper
     */
    public static IdentifierWrapper withDefaultNamespace(String path) {
        return new IdentifierWrapper("minecraft", path);
    }

    /**
     * Tries to parse a string into an IdentifierWrapper.
     *
     * @param identifier the string to parse
     * @return the parsed IdentifierWrapper, or null if parsing failed
     */
    public static @Nullable IdentifierWrapper tryParse(String identifier) {
        //? if >=1.21.11 {
        var parsed = Identifier.tryParse(identifier);
        //?} else {
        /*var parsed = ResourceLocation.tryParse(identifier);
         *///?}
        if (parsed == null) {
            return null;
        }
        return IdentifierWrapper.wrap(parsed);
    }

    /**
     * Gets the namespace of the Identifier.
     *
     * @return the namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Gets the path of the Identifier.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Unwraps the IdentifierWrapper to a Minecraft Identifier.
     *
     * @return the unwrapped Identifier
     */
    //? if >=1.21.11 {
    public Identifier unwrap() {
        return Identifier.fromNamespaceAndPath(namespace, path);
    }
    //?} else {
    /*public ResourceLocation unwrap() {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
    *///?}
}
