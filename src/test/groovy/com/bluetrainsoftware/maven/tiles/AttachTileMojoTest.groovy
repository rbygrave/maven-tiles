package com.bluetrainsoftware.maven.tiles

import org.apache.maven.project.MavenProject
import org.apache.maven.project.MavenProjectHelper
import org.codehaus.plexus.logging.Logger
import org.junit.Test

import static org.mockito.Mockito.mock

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 */
class AttachTileMojoTest {
	@Test
	public void okTile() {
		AttachTileMojo attach = new AttachTileMojo() {
			@Override
			File getTile() {
				return new File("src/test/resources/session-license-tile.xml")
			}
		}

		attach.project = [
			getPackaging: { -> return "tile" }
		] as MavenProject


		File foundTile = null

		attach.projectHelper = [
			attachArtifact: { MavenProject project, String packaging, String classifier, File tile ->
				foundTile = tile
			}
		] as MavenProjectHelper

		attach.logger = mock(Logger)

		attach.execute()

		assert foundTile.exists()
	}
}
